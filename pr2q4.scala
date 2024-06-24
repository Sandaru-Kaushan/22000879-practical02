def findnumberattendence(tipriz:Int) = 120 + ((15 - tipriz)/5) * 20

def fullmoney(tipriz:Int) = (findnumberattendence(tipriz) * tipriz)

def cost(tipriz:Int) = 500 + findnumberattendence(tipriz) * 3

def profit(tipriz:Int) = fullmoney(tipriz) - cost(tipriz)