import models._
import com.github.tototoshi.csv._
import java.io.File
import models.Listing


object Demo {
  @main def run() = {
    val listings = CsvLoader.loadCsv() // Load the data from the CSV file

    /**
      * Display the number of listings in the CSV file.
      */
    println(s"${listings.size} listings have been retrieved from the CSV file.")
    println("-----------------------------------------\n")

    /**
      * METHOD 1 : Filter the listings that have at least 'X' reviews
      */
    val minReviews = scala.io.StdIn.readLine("Enter the minimum number of reviews: ").toInt
    val filteredListings = DataExplorer.filterByMinReviews(listings, minReviews)
    println(s"${filteredListings.size} listings with at least ${minReviews} reviews have been found.") 

    /**
      * METHOD 2 : Calculate the average reviews per month for a given list of Listings
      */
    var listingsAvg = filteredListings
    val avg = DataExplorer.calculateAverageReviewsPerMonth(listings)
    println(s"These listings have an average of ${avg} reviews per month.")

    /**
      * METHOD 3 : Retrieve the 5 listings with the most reviews
      */
    val listingsByMostReviews = DataExplorer.getTopListingsByReviews(listings, 5)
    println("-----")
    println("Here are the 5 listings with the most reviews: ")
    println()
    listingsByMostReviews.foreach { listing =>
      println(s"Listing Name: ${listing.name}")
      println(s"Room type: ${listing.roomType}")
      println(s"Price: ${listing.price}")
      println(s"Number of Reviews: ${listing.numberOfReviews}")
      println("-------------------------")
    }

    /**
      * METHOD 4 : Filter the listings that match a given price range
      */
    println()
    println("What is your price range ?")
    val minPriceInput = scala.io.StdIn.readLine("Enter the minimum price: ")
    val maxPriceInput = scala.io.StdIn.readLine("Enter the maximum price: ")
    try {
      val minPrice = minPriceInput.toDouble
      val maxPrice = maxPriceInput.toDouble

      val filteredByPriceRange = DataExplorer.filterListingsByPriceRange(listings, minPrice, maxPrice)
      println(s"Here are the cheapest listings within your price range: ")
      println("-------------------------")
      filteredByPriceRange.foreach(println)
    } catch {
      case _: NumberFormatException =>
        println("Invalid price input.")
        println("-------------------------")
        println()
    }
    
    /**
      * METHOD 5 : Display all the listings of a given room type
      */
    val roomTypeInput = scala.io.StdIn.readLine("Enter a room type number (1 = EntireHome, 2 = PrivateRoom, 3 = HotelRoom, 4 = UnknownRoom): ")
    val roomType = roomTypeInput.toInt match {
      case 1 => EntireHome
      case 2 => PrivateRoom
      case 3 => HotelRoom
      case 4 => UnknownRoom
      case _ => null
    }
    if (roomType != null) {
      val listingsByRoomType = DataExplorer.getListingsByRoomType(filteredListings, roomType)
      println(s"Here are 5 ${roomType} listings we could find: ")
      println()
      listingsByRoomType.take(5).foreach(println)
    } else {
      println("Invalid room type number.")
      println("-------------------------")
    }

    /**
      * METHOD 6 : Calculate the average price for each room type in a given neighborhood
      */
    val neighborhood = scala.io.StdIn.readLine("Enter a neighborhood: ").toString()
    val avgPriceRoomType = DataExplorer.calculateAveragePricePerNightByRoomTypeAndNeighborhood(listings, neighborhood)
    if (avgPriceRoomType.nonEmpty) {
      println(s"Below are the average prices for each room type in $neighborhood:")
      avgPriceRoomType.foreach { case (roomType, averagePrice) =>
        println(s"Room Type: ${roomType.toString}")
        println(f"Average Price: $averagePrice%.2f")
        println("-------------------------")
      }
    } else {
      println("Nothing could be calculated for the specified neighborhood.")
    }
    println("-----------------------------------------\n")
  }
}
