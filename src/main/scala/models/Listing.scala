package models

import models.{Host, Location, RoomType}

// Definition of the Listing class
case class Listing(
  id: Long,
  name: String,
  host: Host,
  location: Location,
  roomType: RoomType,
  price: Double,
  minimumNights: Int,
  numberOfReviews: Int,
  lastReview: Option[String],
  reviewsPerMonth: Double,
  calculatedHostListingsCount: Int,
  availability_365: Int,
  numberOfReviewsLtm: Int,
  license: Option[String]
) {
  override def toString: String = {
    s"Name: $name\n" +
    s"Location: ${location.neighbourhood}\n" +
    s"Room Type: $roomType\n" +
    s"Price: $price\n" +
    s"Minimum Nights: $minimumNights\n" +
    s"Number of Reviews: $numberOfReviews\n" +
    s"Last Review: ${lastReview.getOrElse("N/A")}\n" +
    s"Availability (365 days): $availability_365\n" +
    s"-----------------------------------------"
  }
}
