import models._
import com.github.tototoshi.csv._
import java.io.File

object Demo {
   @main def run() = {
    println("Welcome to my program")

    // Load the data from the CSV file
    val listings = CsvLoader.loadCsv()

    // Print the number of listings loaded
    println(s"Loaded ${listings.size} listings from the CSV file.")

    // Filter listings with at least 10 reviews
    val filteredListings = DataExplorer.filterByMinReviews(listings, 10)

    // Print the number of listings after filtering
    println(s"Found ${filteredListings.size} listings with at least 10 reviews.")
    
    // Optionally, print the filtered listings
    filteredListings.take(10).foreach(println)
  }
}