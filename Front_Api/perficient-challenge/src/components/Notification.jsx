import React from 'react'
import './notification-styles.css'

function Notification() {
  return (
    <div className='notification-card'>
        <div style={{height: '100%', width: '2px', backgroundColor: '#CCCCCC'}}></div>
      <h4>Notificacion de ejemplo</h4>
      <p>Texto de ejemplo</p>
    </div>
  )
}

export default Notification
