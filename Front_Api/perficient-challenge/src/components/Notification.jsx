import React from 'react'
import './notification-styles.css'

function Notification() {
  return (
    <div className='notification-card'>
        <div className='notification-card__decoration'></div>
        <div className='notification-card__content'>    
            <h4>Notificacion de ejemplo</h4>
            <p>Texto de ejemplo</p>
        </div>
    </div>
  )
}

export default Notification
