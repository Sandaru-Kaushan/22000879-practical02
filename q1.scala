object WarehouseInventorySystem extends App {

  // Inventory maps with product details: ID -> (Name, Quantity, Price)
  val inventory1: Map[Int, (String, Int, Double)] = Map(
    101 -> ("ProductA", 10, 20.0),
    102 -> ("ProductB", 5, 30.0),
    103 -> ("ProductC", 20, 15.0)
  )

  val inventory2: Map[Int, (String, Int, Double)] = Map(
    101 -> ("ProductA", 15, 25.0),
    104 -> ("ProductD", 7, 40.0)
  )

  // I. Retrieve all product names from inventory1
  def retrieveProductNames(inventory: Map[Int, (String, Int, Double)]): List[String] = {
    inventory.values.map(_._1).toList
  }

  // II. Calculate the total value of all products in inventory1
  def calculateTotalValue(inventory: Map[Int, (String, Int, Double)]): Double = {
    inventory.values.map { case (_, qty, price) => qty * price }.sum
  }

  // III. Check if inventory1 is empty
  def isInventoryEmpty(inventory: Map[Int, (String, Int, Double)]): Boolean = {
    inventory.isEmpty
  }

  // IV. Merge inventory1 and inventory2, updating quantities and retaining the highest price
  def mergeInventories(
    inventory1: Map[Int, (String, Int, Double)],
    inventory2: Map[Int, (String, Int, Double)]
  ): Map[Int, (String, Int, Double)] = {
    inventory2.foldLeft(inventory1) {
      case (acc, (id, (name, qty, price))) =>
        acc.get(id) match {
          case Some((_, existingQty, existingPrice)) =>
            acc.updated(id, (name, existingQty + qty, math.max(existingPrice, price)))
          case None => acc.updated(id, (name, qty, price))
        }
    }
  }

  // V. Check if a product with a specific ID exists and print its details
  def checkAndPrintProductDetails(
    inventory: Map[Int, (String, Int, Double)],
    productId: Int
  ): Unit = {
    inventory.get(productId) match {
      case Some((name, qty, price)) =>
        println(s"Product ID: $productId, Name: $name, Quantity: $qty, Price: $price")
      case None => println(s"Product ID: $productId does not exist.")
    }
  }

  // Execute the operations
  println("Product names in inventory1: " + retrieveProductNames(inventory1))

  println("Total value of all products in inventory1: " + calculateTotalValue(inventory1))

  println("Is inventory1 empty? " + isInventoryEmpty(inventory1))

  val mergedInventory = mergeInventories(inventory1, inventory2)
  println("Merged inventory: " + mergedInventory)

  checkAndPrintProductDetails(inventory1, 102)
}
