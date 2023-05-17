package models

import models.ContactInfo

// Definition of the Host class, which extends ContactInfo trait

case class Host(host_id: Int, host_name: String) extends ContactInfo
