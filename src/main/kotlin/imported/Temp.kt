package imported

import com.github.javaparser.ast.CompilationUnit
import com.github.javaparser.ast.Node
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration
import com.github.javaparser.ast.body.FieldDeclaration
import com.github.javaparser.ast.body.MethodDeclaration
import com.github.javaparser.ast.stmt.ExpressionStmt
import com.github.javaparser.ast.stmt.Statement
import imported.classes.TrieNode
import imported.implementations.accept
import imported.interfaces.Visitor

// Esboço para futura implementação de estrutura utilizando Trie para os varios IDs e keywords no codigo
class Temp(private val clazz: ClassOrInterfaceDeclaration) {

    //  KEYWORDS
    val independentKeywords = listOf("if", "for", "while", "switch", "return", "try",
        "int", "boolean", "string", "char", "byte", "double", "float", "short")

    // OPERATORS
    val expressionOperators = listOf("+", "++", "-", "--", "*", "/", "%",
        "=", "==", "<", "<=", ">", ">=", "!=")

    // Dependent on Context:
    val insideDependentKeywords = listOf("break", "continue")
    val afterDependentKeywords = listOf("else", "catch", "finally")
    val declarationOperators = listOf("=", "+=", "-=", "*=", "/=", "%=")

    val importTrie: TrieNode get() {
        val possibilities = TrieNode()
        for(importDeclaration in (clazz.parentNode.get() as CompilationUnit).imports)
            possibilities.insert(
                importDeclaration.nameAsString.split(".").last())
        return possibilities
    }

    val fieldTrie: TrieNode get() {
        val possibilities = TrieNode()
        for(fd in clazz.findAll(FieldDeclaration::class.java))
            for(vd in fd.variables)
                possibilities.insert(vd.nameAsString)
        return possibilities
    }

    val methodsTrie: MutableMap<MethodDeclaration, TrieNode>
        get() {
            val listOfPossibilities = mutableMapOf<MethodDeclaration, TrieNode>()
            for(method in clazz.findAll(MethodDeclaration::class.java))
                listOfPossibilities[method] = TrieNode(getVariablesNames(method))
            return listOfPossibilities
        }

    fun getAllMethodPossibilities(stmt: Statement): Map<MethodDeclaration, TrieNode> {
        val listOfPossibilities = mutableMapOf<MethodDeclaration, TrieNode>()
        for(method in clazz.findAll(MethodDeclaration::class.java))
            listOfPossibilities[method] = TrieNode(getVariablesNames(method, stmt))
        return listOfPossibilities
    }

    // PRIVATE METHODS
    private fun getVariablesNames(method: MethodDeclaration, stmt: Statement? = null): List<String> {
        val v = object: Visitor {
            var names = mutableListOf<String>()
            val line = stmt?.begin?.get()?.line ?: -1
            var condition = true
            override fun visit(s: Statement) {
                stmt?.let {
                    condition = s.begin.get().line <= line ||
                            !isParentNode(stmt.parentNode.get(), s)
                } ?: run { condition = true }
                if(condition && s is ExpressionStmt)
                        if (s.expression.isVariableDeclarationExpr)
                            s.expression.asVariableDeclarationExpr()
                                .variables.forEach { names.add(it.nameAsString) }
            }
        }
        method.body.get().accept(v)
        return v.names
    }

    private fun isParentNode(parent: Node, child: Node): Boolean{
        var isParentNode = false
        fun aux(node: Node){
            if(!isParentNode && node.parentNode.isPresent) {
                val childParent = node.parentNode.get()
                isParentNode = parent == childParent
                aux(childParent)
            }
        }
        aux(child)
        return isParentNode
    }
}