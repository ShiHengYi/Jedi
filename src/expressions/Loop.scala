package expressions

import values._
import ui._

class Loop(sum: Expression, exp : Expression) extends SpecialForm{
  
   def execute(env : Environment) : Value = {
    val result = sum.execute(env)
    
    if (!result.isInstanceOf[Number]) {
      throw new TypeException("loop must be of type number")
    }
    
    var cond = result.asInstanceOf[Number]
    var num = cond.toString().toInt
    var count = 0
    while(count < num-1){
      exp.execute(env);
      count += 1
    }
    exp.execute(env);
  }
   
}