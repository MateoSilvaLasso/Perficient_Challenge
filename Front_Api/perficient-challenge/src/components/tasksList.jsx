import React, { useEffect } from "react";
import { useState } from "react";
import TaskCard  from './TaskCard.jsx'
import './task-container-styles.css'

function TasksList({tasks, openCardEdit}) {
  
  return (
    <div className="task-container">
      {tasks.map((task, index) => (
        <div key={index} >
          <TaskCard task = {task} openCardEdit = {openCardEdit}/>
        </div>
      ))}
    </div>
  );
}

export default TasksList;
