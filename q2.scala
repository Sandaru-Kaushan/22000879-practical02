import scala.io.StdIn

object StudentRecordsManager extends App {

  def getStudentInfo(): (String, Int, Int, Double, Char) = {
    val (name, marks, totalMarks) = getStudentInfoWithRetry()
    val percentage = (marks.toDouble / totalMarks) * 100
    val grade = calculateGrade(percentage)
    (name, marks, totalMarks, percentage, grade)
  }

  def printStudentRecord(student: (String, Int, Int, Double, Char)): Unit = {
    val (name, marks, totalMarks, percentage, grade) = student
    println(s"Student Name: $name")
    println(s"Marks: $marks / $totalMarks")
    println(f"Percentage: $percentage%.2f%%")
    println(s"Grade: $grade")
  }

  def validateInput(name: String, marks: String, totalMarks: String): (Boolean, Option[String]) = {
    if (name.trim.isEmpty) {
      (false, Some("Name cannot be empty"))
    } else if (!marks.forall(_.isDigit) || !totalMarks.forall(_.isDigit)) {
      (false, Some("Marks and Total Marks should be positive integers"))
    } else if (marks.toInt < 0 || totalMarks.toInt <= 0) {
      (false, Some("Marks must be non-negative and Total Marks must be positive"))
    } else if (marks.toInt > totalMarks.toInt) {
      (false, Some("Marks cannot exceed Total Marks"))
    } else {
      (true, None)
    }
  }

  def getStudentInfoWithRetry(): (String, Int, Int) = {
    var valid = false
    var name = ""
    var marks = ""
    var totalMarks = ""

    while (!valid) {
      println("Enter student name:")
      name = StdIn.readLine()

      println("Enter marks obtained:")
      marks = StdIn.readLine()

      println("Enter total possible marks:")
      totalMarks = StdIn.readLine()

      val (isValid, errorMessage) = validateInput(name, marks, totalMarks)
      if (isValid) {
        valid = true
      } else {
        println(s"Invalid input: ${errorMessage.get}")
      }
    }

    (name, marks.toInt, totalMarks.toInt)
  }

  def calculateGrade(percentage: Double): Char = {
    percentage match {
      case p if p >= 90 => 'A'
      case p if p >= 75 => 'B'
      case p if p >= 50 => 'C'
      case _ => 'D'
    }
  }

  // Get student info and print the record
  val studentInfo = getStudentInfo()
  printStudentRecord(studentInfo)
}
