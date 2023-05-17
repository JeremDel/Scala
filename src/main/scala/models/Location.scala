package models


// Definition of the Location class
case class Location(neighbourhoodGroup: Option[String], neighbourhood: String, coordinate: Coordinate)
