import React from "react";
import { useState, useEffect } from "react";
import TasksList from "../components/tasksList";
import TaskForm from "../components/TaskForm";
import { data } from "../components/tasks.js";
import ButtonAppBar from "../components/Header.jsx";
import { IoAddCircleOutline } from "react-icons/io5";
import { MdOutlineCancel } from "react-icons/md";
import TextField from "@mui/material/TextField";
import InputBase from "@mui/material/InputBase";
import IconButton from "@mui/material/IconButton";
import SearchIcon from "@mui/icons-material/Search";
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
            <div style={{ display: "flex", justifyContent: "flex-end" }}>
              <button
                id="close"
                onClick={() => {
                  setAddTaskVisibility(false);
                }}
              >
                <MdOutlineCancel size="30px" />
              </button>
            </div>
            <TaskForm
              createTask={createTask}
              closeWindow={setAddTaskVisibility}
            />
          </div>
        </div>
      ) : null}

      <div style={{ height: "10vh" }}>
        <ButtonAppBar />
      </div>
      <section className="main-content">
        <div className="task-form">
          <h1>Notificaciones</h1>
        </div>

        <div className="task-list">
          <div className="nav-bar-section">
            <div style={{border: '1px solid rgba(0,0,0,0.2)', borderRadius: '4rem', paddingLeft: '1rem'}}>
              <InputBase
                sx={{ ml: 1, flex: 1, width: '30rem' }}
                placeholder="Buscar"
                inputProps={{ "aria-label": "search google maps" }}
              />
              <IconButton type="button" sx={{ p: "10px" }} aria-label="search">
                <SearchIcon />
              </IconButton>
            </div>
            <button
              onClick={() => {
                setAddTaskVisibility(true);
              }}
            >
              <IoAddCircleOutline className="add-icon" size="3rem" />
            </button>
          </div>
          <TasksList tasks={tasks} />
        </div>
      </section>
    </div>
  );
}

export default Tasks;
