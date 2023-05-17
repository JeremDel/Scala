package models

import models.{Host, Location, RoomType}

// Definition of the Listing class
case class Listing(
  id: Int,
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
)
