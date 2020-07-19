import java.util

object code_20 {
  def isValid(s: String): Boolean = {

    val stack : util.Stack[Character] = new util.Stack()
    for(c <- s){
      if (c == '(' || c == '[' || c == '{') {stack.push(c)}
      else {
        if (stack.isEmpty) return false
        val topStack = stack.pop
        if (c == ')' && topStack != '(') return false
        if (c == ']' && topStack != '[') return false
        if (c == '}' && topStack != '{') return false
      }
    }
    return stack.isEmpty
  }

  def main(args: Array[String]): Unit = {
    val s : String = "{}(){"
    print(isValid(s))
  }

}