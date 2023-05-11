import java.io.File
import com.github.tototoshi.csv._

object CsvLoader {
  def loadCsv(file: File): List[Listing] = {
    val reader = CSVReader.open(file)
    val rows = reader.allWithHeaders()
    reader.close()

    rows.map { row =>
      val host = Host(row("host_id").toInt, row("host_name"))
      val coordinate = Coordinate(row("latitude").toDouble, row("longitude").toDouble)
      val location = Location(row.get("neighbourhood_group"), row("neighbourhood"), coordinate)
      val roomType = row("room_type") match {
        case "Entire home/apt" => EntireHome
        case "Private room" => PrivateRoom
        case _ => throw new IllegalArgumentException("Invalid room type")
      }
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
        reviewsPerMonth = row("reviews_per_month").toDouble,
        calculatedHostListingsCount = row("calculated_host_listings_count").toInt,
        availability_365 = row("availability_365").toInt,
        numberOfReviewsLtm = row("number_of_reviews_ltm").toInt,
        license = row.get("license")
      )
    }
  }
}
