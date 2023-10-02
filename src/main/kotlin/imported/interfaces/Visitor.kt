package imported.interfaces

import com.github.javaparser.ast.stmt.BlockStmt
import com.github.javaparser.ast.stmt.Statement

interface Visitor {
    fun visit(s: Statement) { }
    fun visit(s: BlockStmt): Boolean = true
    fun endVisit(s: BlockStmt) { }
}