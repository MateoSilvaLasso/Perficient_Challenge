import React from "react";
import { useState, useEffect } from "react";
import TasksList from "../components/tasksList";
import TaskForm from "../components/TaskForm";
import { data } from "../components/tasks.js";
import ButtonAppBar from "../components/Header.jsx";
import { IoAddCircleOutline } from "react-icons/io5";
import { MdOutlineCancel } from "react-icons/md";
import Notifications from "../components/Notifications.jsx";
import { IoIosSearch } from "react-icons/io";
import MenuItem from "@mui/material/MenuItem";
import Select from "@mui/material/Select";
import InputLabel from "@mui/material/InputLabel";
import FormControl from "@mui/material/FormControl";
import "./task-styles.css";

function Tasks() {
  const [tasks, setTasks] = useState([]);
  const [addTaskVisibility, setAddTaskVisibility] = useState(false);
  const [categoryQuery, setCategoryQuery] = useState();
  const [stateQuery, setStateQuery] = useState();
 
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
        <div className="task-notification">
          <Notifications />
        </div>

        <div className="task-list">
          <div className="nav-bar-section">
            <div className="seach-bar-container">
              <input
                placeholder="Escribe tu busqueda"
                className="search-bar"
                type="text"
              />
              <select name="" id="">
                <option value="" selected>Buscar por</option>
                <option value="Casa">Titulo</option>
                <option value="Universidad">Informacion</option>
                <option value="Trabajo">Trabajo</option>
              </select>
              <select name="" id="">
                <option value="" selected>Categoria</option>
                <option value="Casa">Casa</option>
                <option value="Universidad">Universidad</option>
                <option value="Trabajo">Trabajo</option>
              </select>
              <select name="" id="">
                <option value="" selected>Estado</option>
                <option value="Casa">Por hacer</option>
                <option value="Universidad">En proceso</option>
                <option value="Trabajo">Finalizado</option>
                <option value="Trabajo">Cancelado</option>
              </select>
              <button>
                <IoIosSearch size="30px" color="#1F1F1F" style={{marginLeft: '30px'}}/>
              </button>
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
