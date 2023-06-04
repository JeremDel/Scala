import models._

object DataExplorer {
  /**
    * This method filters a list of listings based on a given minimum number of reviews
    *
    * @param listings : the list of listings that will be filtered
    * @param minReviews : the minimum number of reviews each listing must have
    * @return : the list of listings that have at least 'minReviews' number of reviews
    */
  def filterByMinReviews(listings: List[Listing], minReviews: Int): List[Listing] = {
    listings.filter(_.numberOfReviews >= minReviews)
  }

  /**
    * This method retrieves the X listings with the most reviews for a given list of listings
    *
    * @param listings : the list of listings that will be filtered
    * @param count : the number of listings we want to retrieve
    * @return : the sorted list of X listings with the most reviews
    */
  def getTopListingsByReviews(listings: List[Listing], count: Int): List[Listing] =
    listings.sortBy(-_.numberOfReviews).take(count)

  /**
    * This method filters a given list of listings based on the type of room
    *
    * @param listings : the list of listings that will be filtered
    * @param roomType : the type of room we want to retrieve
    * @return : the list of listings matching the given type of room
    */
  def getListingsByRoomType(listings: List[Listing], roomType: RoomType): List[Listing] =
    listings.filter(_.roomType == roomType)

  /**
    * This method calculates the average number of reviews per listing for a given list of listings
    *
    * @param listings : the list of listings that will be used to calculate the average number of reviews 
    * @return : the average number of reviews for the given listings (as a 'Double')
    */
  def calculateAverageReviewsPerMonth(listings: List[Listing]): Double = {
    val totalReviewsPerMonth = listings.foldLeft(0.0)(_ + _.reviewsPerMonth)
    val numListings = listings.size
    totalReviewsPerMonth / numListings
  }
  
  /**
    * This method filters a given list of listings based on a given price range
    *
    * @param listings : the list of listings that will be filtered
    * @param minPrice : the minimum amount for the price range
    * @param maxPrice : the maximum amount for the price range
    * @return : the list of listings matching the given price range
    */
  def filterListingsByPriceRange(listings: List[Listing], minPrice: Double, maxPrice: Double): List[Listing] = {
    if (minPrice.isNaN || maxPrice.isNaN) {
      println("Invalid price input. Please enter valid numbers.")
      List.empty[Listing]
    } else {
      val filteredListings = listings.filter { listing =>
        val listingPrice = listing.price
        listingPrice >= minPrice && listingPrice <= maxPrice
      }
      filteredListings.sortBy(_.price).take(5)
    }
  }

  /**
    * This method calculates the average price per night for each room type
    *
    * @param listings : the list of listings used to calculate the average price
    * @param neighborhood : the given neighborhood for which we want to calculate the average price
    * @return : a map of room types and their average price per night
    */
  def calculateAveragePricePerNightByRoomTypeAndNeighborhood(listings: List[Listing], neighborhood: String): Map[RoomType, Double] = {
    val filteredListings = listings.filter(_.location.neighbourhood == neighborhood)
    if (filteredListings.isEmpty) {
      Map.empty[RoomType, Double]
    } else {
      val groupedByRoomType = filteredListings.groupBy(_.roomType)
      val averagePriceByRoomType = groupedByRoomType.mapValues { listings =>
        val totalPrice = listings.map(_.price).sum
        val numListings = listings.size
        totalPrice / numListings
      }
      averagePriceByRoomType.toMap
    }
  }
}
