import React from 'react'
import './notifications-styles.css'
import Notification from './Notification'

function Notifications() {
  return (
    <div className='main-container-notifications'>
      <h1>Notificaciones</h1>
      <div className='notification-container'>
        <Notification/>
        <Notification/>
        <Notification/>
        <Notification/>
        <Notification/>
      </div>
    </div>
  )
}

export default Notifications
