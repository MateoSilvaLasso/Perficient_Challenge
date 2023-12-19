import React from 'react'
import './notification-styles.css'

function Notification() {
  return (
    <div className='notification-card'>
        <div className='notification-card__decoration'></div>
        <div className='notification-card__content'>    
            <h4>Recordatorio</h4>
            <p>Proximamente se vencen</p>
        </div>
    </div>
  )
}

export default Notification
