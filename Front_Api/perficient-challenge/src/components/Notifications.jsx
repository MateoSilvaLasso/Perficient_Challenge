import "./notifications-styles.css";
import Notification from "./Notification";
import { useState, useEffect } from "react";
import axios from "../config/axios";

function Notifications() {

  const [notifications, setNotifications] = useState();

  useEffect( () => {
    // axios.get(`/tasks/${vari}`).then((res) => {
    //   if (res.status === 200) {
    //     console.log(res.data);
    //     setTasks(res.data);
    //   }
    // });
  } )
  
  return (
    <>
      <div className="main-container-notifications">
        <h1 style={{ fontSize: "1.5rem" }}>
          {" "}
          <b>Notificaciones</b>
        </h1>
        <div className="notification-container">
          <Notification />
          <Notification />
          
        </div>
      </div>
    </>
  );
}

export default Notifications;
