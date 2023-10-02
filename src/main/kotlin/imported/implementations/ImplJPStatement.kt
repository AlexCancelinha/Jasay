package imported.implementations

import com.github.javaparser.ast.stmt.BlockStmt
import com.github.javaparser.ast.stmt.Statement
import imported.interfaces.Visitor

fun Statement.accept(v: Visitor){
    v.visit(this)
}

fun BlockStmt.accept(v: Visitor){
    if(v.visit(this))
        statements.forEach {
            // IF STATEMENT
            if(it.isIfStmt)
                if(it.asIfStmt().thenStmt.isBlockStmt)
                    it.asIfStmt().thenStmt.asBlockStmt().accept(v)
                else
                    it.asIfStmt().thenStmt.accept(v)

            // WHILE STATEMENT
            else if(it.isWhileStmt)
                if(it.asWhileStmt().body.isBlockStmt)
                    it.asWhileStmt().body.asBlockStmt().accept(v)
                else
                    it.asWhileStmt().body.accept(v)

            // DO-WHILE STATEMENT
            else if(it.isDoStmt)
                if(it.asDoStmt().body.isBlockStmt)
                    it.asDoStmt().body.asBlockStmt().accept(v)
                else
                    it.asDoStmt().body.accept(v)

            // FOR STATEMENT
            else if(it.isForStmt)
                if(it.asForStmt().body.isBlockStmt)
                    it.asForStmt().body.asBlockStmt().accept(v)
                else
                    it.asForStmt().body.accept(v)

            // FOREACH STATEMENT
            else if(it.isForEachStmt)
                if(it.asForEachStmt().body.isBlockStmt)
                    it.asForEachStmt().body.asBlockStmt().accept(v)
                else
                    it.asForStmt().body.accept(v)

            // TRY STATEMENT
//            else if(it.isTryStmt){
//                it.asTryStmt().tryBlock.accept(v)
//                it.asTryStmt().catchClauses.forEach{ cc -> cc.body.accept(v) }
//                if(it.asTryStmt().finallyBlock.isPresent)
//                    it.asTryStmt().finallyBlock.orElseThrow().accept(v)
//            }
//
//            else
//                it.accept(v)
        }
    v.endVisit(this)
}