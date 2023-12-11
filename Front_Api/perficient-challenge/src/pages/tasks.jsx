import React from 'react'
import { useState, useEffect  } from 'react'
import TasksList from '../components/tasksList'
import TaskForm from '../components/TaskForm'
import { data } from '../components/tasks.js'
import ButtonAppBar from '../components/Header.jsx'
import './task-styles.css'

function Tasks() {
  
  const [tasks, setTasks] = useState([]);

  useEffect(() => {
    setTasks(data);
  }, [ ]);

  function createTask(task) {
    setTasks([...tasks, task ])
  }

  return (
    <div>
      <div style = {{height: '10vh'}}>
        <ButtonAppBar/>
      </div>
      <h1>Bienvenido a tus Tareas</h1>
      <section className='main-content'>
        <div className='task-form'>
          <TaskForm createTask = {createTask}/>
        </div>
        
        <div className='task-list'>
          <TasksList tasks = {tasks}/>
        </div>
      </section>
    </div>
  )
}

export default Tasks
