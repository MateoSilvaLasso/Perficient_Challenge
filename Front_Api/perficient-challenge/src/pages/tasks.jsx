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
import CardEdit from "../components/CardEdit.jsx";
import "./task-styles.css";
import { categories } from '../components/Categories.js'

function Tasks() {
  const [tasks, setTasks] = useState([]);
  const [addTaskVisibility, setAddTaskVisibility] = useState(false);
  const [categoryQuery, setCategoryQuery] = useState();
  const [stateQuery, setStateQuery] = useState();
  const [addCategoryQuery, setAddCategoryQuery] = useState(false);
  const [cardDescription, setCardDescription] = useState(null);

  useEffect(() => {
    setTasks(data);
  }, []);

  function createTask(task) {
    setTasks([...tasks, task]);
  }

  useEffect(() => {
    console.log("Ayudame dios");
    console.log(cardDescription);
  }, [cardDescription]);

  return (
    <div>
      {cardDescription !== null ? (
        <CardEdit task={cardDescription} closeWindow={setCardDescription} />
      ) : null}

      {addCategoryQuery ? (
        <div className="modal-container" id="modal_container">
        <div className="modal-help">
          <div style={{ display: "flex", justifyContent: "flex-end" }}>
            <button
              id="close"
              onClick={() => {
                setAddCategoryQuery(false);
              }}
            >
              <MdOutlineCancel size="30px" />
            </button>
          </div>
          <h3 style={{marginBottom: '2rem'}}>Crear categorias</h3>
          {categories.map((category, index) => (
            <div style={{display: 'flex', justifyContent: 'space-between', alignItems: 'center', height: '3rem', border: '1px solid rgba(0,0,0,0.2)', borderRadius: '10px', marginBottom: '1rem'}}>
              <p style={{marginLeft: '1rem'}} key={index}>{category}</p>
              <div style={{width: '1rem', height: '1rem', backgroundColor: 'black', marginRight: '2rem', borderRadius: '10px'}}></div>
            </div>
          ))}
          
          <div style={{display: 'flex', justifyContent: 'space-between', alignItems: 'center', height: '3rem', border: '1px solid rgba(0,0,0,0.2)', borderRadius: '10px', marginBottom: '1rem'}}>
            <input style={{marginLeft: '1rem', borderRadius: '15px', paddingLeft: '1rem', border: '1px solid rgba(0,0,0,0.3)'}} type="text" placeholder="Escribe la categoria" />
            <input  style={{marginRight: '1rem'}} type="color" name="" id=""  />
            </div>
            <div style={{display: 'flex', justifyContent: 'center', width: '100% '}}>
              <button style={{border: '1px solid rgba(0,0,0,0.3)', padding: '10px 15px', borderRadius: '5px', backgroundColor: 'black', color: 'white'}} onClick={() => {
                setAddCategoryQuery(false)
              }}>Crear la catetoria</button>
            </div>
            
        </div>
      </div>
      ) : null }

      {addTaskVisibility ? (
        <div className="modal-container" id="modal_container">
          <div className="modal-help">
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
          <div style={{height: '10vh', display: 'flex', alignItems: 'center', marginLeft: '3rem'}}>
            <button style={{border: '2px solid rgba(0,0,0,0.3)', borderRadius: '25px', padding: '5px 10px 5px 10px ', backgroundColor: 'rgba(0,0,0,0.7)', color: 'white', fontWeight: '600'}} onClick={ () => {
              setAddCategoryQuery(true)
            } }> Configurar categorias</button>
          </div>
          <div className="task-notification-fix">
            <Notifications />
          </div>
        </div>

        <div className="task-list">
          <div className="nav-bar-section">
            <div className="seach-bar-container">
              <input
                style={{ marginRight: "40px", width: "40rem" }}
                placeholder="Escribe tu busqueda"
                className="search-bar"
                type="text"
              />
              <select name="" id="" style={{ marginRight: "20px" }}>
                <option value="" selected>
                  Buscar por
                </option>
                <option value="Casa">Titulo</option>
                <option value="Universidad">Informacion</option>
              </select>
              <select name="" id="" style={{ marginRight: "20px" }}>
                <option value="" selected>
                  Categoria
                </option>
                <option value="Casa">Casa</option>
                <option value="Universidad">Universidad</option>
                <option value="Trabajo">Trabajo</option>
              </select>
              <select name="" id="" style={{ marginRight: "20px" }}>
                <option value="" selected>
                  Estado
                </option>
                <option value="Casa">Por hacer</option>
                <option value="Universidad">En proceso</option>
                <option value="Trabajo">Finalizado</option>
                <option value="Trabajo">Cancelado</option>
              </select>
              <button>
                <IoIosSearch
                  size="30px"
                  color="#1F1F1F"
                  style={{ marginLeft: "30px" }}
                />
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
          <TasksList tasks={tasks} openCardEdit={setCardDescription} />
        </div>
      </section>
    </div>
  );
}

export default Tasks;
