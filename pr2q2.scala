object Main extends App {
  var a: Int = 2
  var b: Int = 3
  var c: Int = 4
  var d: Int = 5
  var k: Float = 4.3f

  def evaluateExpressions(): Unit = {
//println(--b * a + c * d--)  it is not working
    b-=1
    println(b * a + c * d)
    d-=1 
    //println(++a) not working in scala becouse it is pre increment
    a += 1
    println(a)  
   // println(-2 * (g - k) + c)  g is not given
    //println (c=c++) not working because scala not support post increment
    println(c)  // Expression d
    c += 1
    c += 1
    println(c * a)  // Expression e
    a += 1
  }

  evaluateExpressions()
}
