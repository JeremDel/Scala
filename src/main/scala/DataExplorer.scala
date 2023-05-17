//This method allows filtering the listings based on the minimum number of reviews.
import models._

object DataExplorer {
  def filterByMinReviews(listings: List[Listing], minReviews: Int): List[Listing] = {
    listings.filter(_.numberOfReviews >= minReviews)
  }
}
