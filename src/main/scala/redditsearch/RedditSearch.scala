package main.scala.redditsearch

object RedditSearch {
  def main(args: Array[String]): Unit = {
    if (args.length > 0) {
      run(args)
    } else {
      val shell = new InteractiveShell
      shell.start
    }
  }

  def run(args: Array[String]) = {
    val also = filterToType(args, '+')
    val filter = filterToType(args, '-')
    val similar = filterToType(args, '~')
    val search = filterToNonType(args, also ++ filter ++ similar)
    println(s"""Searching for "${search}" that exists in the following reddits:""")
    also.foreach(a => println(s"- ${a}"))
    println("That does NOT exist in the following subreddits:")
    filter.foreach(f => println(s"- ${f}"))
    println("That may be similar to:")
    similar.foreach(s => println(s"- ${s}"))

  }

  def filterToType(arr: Array[String], ofType: Char): Array[String] = {
    arr.filter(a => a.charAt(0) == ofType)
  }

  def filterToNonType(elems: Array[String], filters: Array[String]): String = {
    elems.filterNot(e => filters.contains(e)).mkString(" ")
  }

}

class InteractiveShell {
  def start(): Unit = {
    print("What would you like to search for > ")
    val search: Array[String] = scala.io.StdIn.readLine.split(" ")
    print("Any filters? > ")
    val filters: Array[String] = scala.io.StdIn.readLine.split(" ")
    print("Any explicit reddits? > ")
    val also: Array[String] = scala.io.StdIn.readLine.split(" ")
    RedditSearch.run(filters ++ also ++ search)

  }
}
