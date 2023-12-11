import React, { useEffect } from "react";
import { useState } from "react";
import { data } from "./tasks.js";
import TaskCard  from './TaskCard.jsx'
console.log(data);

function TasksList() {

  const [tasks, setTasks] = useState([]);

  useEffect(() => {
    setTasks(data);
  }, [ ]);

  return (
    <div>
      {tasks.map((task, index) => (
        <div key={index} >
          <TaskCard task = {task}/>
        </div>
      ))}
    </div>
  );
}

export default TasksList;
