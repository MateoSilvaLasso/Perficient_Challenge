import React from 'react'
import { useState } from 'react'
import TasksList from '../components/tasksList'

function Tasks() {

  return (
    <div>
      <h1>Bienvenido a tus Tareas</h1>
      <TasksList/>
    </div>
  )
}

export default Tasks
