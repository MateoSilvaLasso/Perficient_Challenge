import React, { useEffect } from "react";
import { useState } from "react";
import TaskCard  from './TaskCard.jsx'

function TasksList({tasks}) {
  
  return (
    <>
      {tasks.map((task, index) => (
        <div key={index} >
          <TaskCard task = {task}/>
        </div>
      ))}
    </>
  );
}

export default TasksList;
