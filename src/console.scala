
import scala.io._
import scala.util.parsing.combinator._
class Sum () {
  
}
class Number() {
  
}
class Product() {
  
}

// TO DO: Complete this declaration:
object sopParsers extends RegexParsers {

  def sum: Parser[Sum] = 

  def product: Parser[Product] = 

  def number: Parser[Number] = (0|[1-9]~[0-9]*)(\.[0-9]+)?
}

class SyntaxException(val result: Parsers#Failure = null) extends Exception("Syntax error")

object console {

   def execute(cmmd: String): String = {
      val tree = sopParsers.parseAll(sopParsers.sum, cmmd)
       tree match {
         case tree: sopParsers.Failure => throw new SyntaxException(tree)
         case _ => {
            val exp = tree.get  // get the expression from the tree
            val result = exp.execute  // execute the expression
            result.toString  // return string representation of result
         }
      }
   }
   
    def repl {
      var more = true
      var cmmd = ""
      while(more) {
         try {
            print("-> ")
            cmmd = StdIn.readLine
            if (cmmd == "quit") more = false
            else println(execute(cmmd))
         } 
         catch {
           case e: SyntaxException => {
               println(e)
               println(e.result.msg)
               println("line # = " + e.result.next.pos.line)
               println("column # = " + e.result.next.pos.column)
               println("token = " + e.result.next.first)
            }
            case e: Exception => {
               println(e)
            }
         }
      }
      println("bye")
   }
    
   def main(args: Array[String]): Unit = { repl }
   
}