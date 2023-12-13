import React from "react";
import { useState, useEffect } from "react";
import TasksList from "../components/tasksList";
import TaskForm from "../components/TaskForm";
import { data } from "../components/tasks.js";
import ButtonAppBar from "../components/Header.jsx";
import { IoAddCircleOutline } from "react-icons/io5";
import { MdOutlineCancel } from "react-icons/md";
import "./task-styles.css";

function Tasks() {
  const [tasks, setTasks] = useState([]);
  const [addTaskVisibility, setAddTaskVisibility] = useState(false);

  useEffect(() => {
    setTasks(data);
  }, []);

  function createTask(task) {
    setTasks([...tasks, task]);
  }

  return (
    <div>
      {addTaskVisibility ? (
        <div className="modal-container" id="modal_container">
          <div className="modal">
            <div style={{display: 'flex', justifyContent: 'flex-end'}}>
              <button
                id="close"
                onClick={() => {
                  setAddTaskVisibility(false);
                }}
              >
                <MdOutlineCancel size = '30px' />
              </button>
            </div>
            <TaskForm createTask={createTask} closeWindow = {setAddTaskVisibility}/>
          </div>
        </div>
      ) : null}
      <div style={{ height: "10vh" }}>
        <ButtonAppBar />
      </div>
      <div className="nav-bar-section">
        <button
          onClick={() => {
            setAddTaskVisibility(true);
          }}
        >
          <IoAddCircleOutline className="add-icon" size="3rem" />
        </button>
      </div>
      <section className="main-content">
        <div className="task-form">
          <h1>Notificaciones</h1>
        </div>

        <div className="task-list">
          <TasksList tasks={tasks} />
        </div>
      </section>
    </div>
  );
}

export default Tasks;
