
package models
// Definition of the sealed trait RoomType and its subtypes
sealed trait RoomType
case object EntireHome extends RoomType
case object PrivateRoom extends RoomType
case object HotelRoom extends RoomType
case object UnknownRoom extends RoomType
