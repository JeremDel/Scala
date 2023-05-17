package models

import com.github.tototoshi.csv._
import java.io.File
import models.{Host, Location, RoomType, EntireHome, PrivateRoom, Coordinate}

object CsvLoader {
  def loadCsv(): List[Listing] = {
    val file = new File("Data\\12-AirBnBLondon.csv")
    val reader = CSVReader.open(file)
    val rows = reader.allWithHeaders()
    reader.close()

    // Mapping each row in the CSV file to a Listing object
    rows.map { row =>
      val host = Host(row("host_id").toInt, row("host_name"))
      val coordinate = Coordinate(row("latitude").toDouble, row("longitude").toDouble)
      val location = Location(row.get("neighbourhood_group"), row("neighbourhood"), coordinate)
     
     // Determining the room type based on the value in the "room_type" column
      val roomType = row("room_type") match {
        case "Entire home/apt" => EntireHome
        case "Private room" => PrivateRoom
        case _ => throw new IllegalArgumentException("Invalid room type")
      }
      // Creating a Listing object with the parsed values
      Listing(
        id = row("id").toInt,
        name = row("name"),
        host = host,
        location = location,
        roomType = roomType,
        price = row("price").toDouble,
        minimumNights = row("minimum_nights").toInt,
        numberOfReviews = row("number_of_reviews").toInt,
        lastReview = row.get("last_review"),
        reviewsPerMonth = row.get("reviews_per_month").map(_.toDouble).getOrElse(0.0),
        calculatedHostListingsCount = row("calculated_host_listings_count").toInt,
        availability_365 = row("availability_365").toInt,
        numberOfReviewsLtm = row("number_of_reviews_ltm").toInt,
        license = row.get("license")
      )
    }
  }
}
