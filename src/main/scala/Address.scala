//definition de RoomType et EntireHome

sealed trait RoomType
case object EntireHome extends RoomType
case object PrivateRoom extends RoomType