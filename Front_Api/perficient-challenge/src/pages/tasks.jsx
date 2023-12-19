import React from "react";
import { useState, useEffect } from "react";
import TasksList from "../components/tasksList";
import TaskForm from "../components/TaskForm";
import { connect } from "react-redux";
import axios from "../config/axios";
import { data } from "../components/tasks.js";
import ButtonAppBar from "../components/Header.jsx";
import { IoAddCircleOutline } from "react-icons/io5";
import { MdOutlineCancel } from "react-icons/md";
import Notifications from "../components/Notifications.jsx";
import { IoIosSearch } from "react-icons/io";
import CardEdit from "../components/CardEdit.jsx";
import { useAuth } from "../pages/authContext";
import "./task-styles.css";
import store from "../store";
import { useGlobalState, setGlobalState } from "../index";
import { useNavigate } from "react-router-dom";
import { categories } from "../components/Categories.js";


function Tasks() {
  const [tasks, setTasks] = useState([]);
  const [addTaskVisibility, setAddTaskVisibility] = useState(false);

  const [categoryQuery, setCategoryQuery] = useState([]);
  const [stateQuery, setStateQuery] = useState([]);
  const [cardDescription, setCardDescription] = useState(null);
  const vari = store.getState();
  let navigate = useNavigate();
  const [addCategoryQuery, setAddCategoryQuery] = useState(false);

  const [nameNewTask, setNameNewTask ] = useState()
  const [colorNewTask, setColorNewTask] = useState()

  useEffect(() => {
    console.log(vari.variableGlobal.type);

    axios.get(`/tasks/${vari.variableGlobal.type}`).then((res) => {
      if (res.status === 200) {
        console.log(res.data);
        setTasks(res.data);
      }
    });

    axios.get("/status").then((res) => {
      if (res.status === 200) {
        console.log(res.data);
        setStateQuery(res.data);
      }
    });

    axios.get("/category").then((res) => {
      if (res.status === 200) {
        console.log(res.data);
        setCategoryQuery(res.data);
      }
    });

    //setTasks(data);
  }, []);

  function createTask(task, category, status) {
    axios.post(`/tasks/${category}/${status}/${vari.variableGlobal.type}`, task).then((res) => {
      if (res.status === 200) {
        console.log(res.data);
        navigate("/App");
      }
    });
  }

  const editTask = (task, id, category, status) => {
    axios.put(`/tasks/${id}/${category}/${status}`, task).then((res) => {
      if (res.status === 200) {
        console.log(res.data);
        navigate("/");
      }
    });
  };

  function searchByStatus(event) {
    const state = event.target.value
    axios.get(`/tasks/${state}/${vari.variableGlobal.type}`).then(res =>{
      if(res.status === 200){
        console.log(res.data)
        setTasks(res.data)
      }
    })
  }

  function searchByCategory(event){
    const category = event.target.value
    axios.get(`/tasks/category/${category}/${vari.variableGlobal.type}`).then(res =>{
      if(res.status === 200){
        console.log(res.data)
        setTasks(res.data)
      }
    })
  }

  useEffect(() => {
    console.log("Ayudame dios");
    console.log(cardDescription);
  }, [cardDescription]);

  return (
    <div>
      {cardDescription !== null ? (
        <CardEdit
          task={cardDescription}
          closeWindow={setCardDescription}
          editTask={editTask}
        />
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
            <h3 style={{ marginBottom: "2rem" }}>Crear categorias</h3>
            {categories.map((category, index) => (
              <div
                style={{
                  display: "flex",
                  justifyContent: "space-between",
                  alignItems: "center",
                  height: "3rem",
                  border: "1px solid rgba(0,0,0,0.2)",
                  borderRadius: "10px",
                  marginBottom: "1rem",
                }}
              >
                <p style={{ marginLeft: "1rem" }} key={index}>
                  {category}
                </p>
                <div
                  style={{
                    width: "1rem",
                    height: "1rem",
                    backgroundColor: "black",
                    marginRight: "2rem",
                    borderRadius: "10px",
                  }}
                ></div>
              </div>
            ))}

            <div
              style={{
                display: "flex",
                justifyContent: "space-between",
                alignItems: "center",
                height: "3rem",
                border: "1px solid rgba(0,0,0,0.2)",
                borderRadius: "10px",
                marginBottom: "1rem",
              }}
            >
              <input
                style={{
                  marginLeft: "1rem",
                  borderRadius: "15px",
                  paddingLeft: "1rem",
                  border: "1px solid rgba(0,0,0,0.3)",
                }}
                type="text"
                placeholder="Escribe la categoria"
                onChange={(e) => setNameNewTask(e.target.value) }
              />
              <input
                style={{ marginRight: "1rem" }}
                type="color"
                name=""
                id=""
                onChange={(e) => setColorNewTask(e.target.value)}
              />
            </div>
            <div
              style={{
                display: "flex",
                justifyContent: "center",
                width: "100% ",
              }}
            >
              <button
                style={{
                  border: "1px solid rgba(0,0,0,0.3)",
                  padding: "10px 15px",
                  borderRadius: "5px",
                  backgroundColor: "black",
                  color: "white",
                }}
                onClick={() => {
                  axios.post(`/category`, {creationTime: new Date(), message: nameNewTask, color: colorNewTask }).then((res) => {
                    if (res.status === 200) {
                      console.log(res.data);
                      navigate("/App");
                    }
                  });
                  setAddCategoryQuery(false);
                }}
              >
                Crear la catetoria
              </button>
            </div>
          </div>
        </div>
      ) : null}

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
          <div
            style={{
              height: "10vh",
              display: "flex",
              alignItems: "center",
              marginLeft: "3rem",
            }}
          >
            <button
              style={{
                border: "2px solid rgba(0,0,0,0.3)",
                borderRadius: "25px",
                padding: "5px 10px 5px 10px ",
                backgroundColor: "rgba(0,0,0,0.7)",
                color: "white",
                fontWeight: "600",
              }}
              onClick={() => {
                axios.post()
                setAddCategoryQuery(true);
              }}
            >
              {" "}
              Configurar categorias
            </button>
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
              <select name="" id="" style={{ marginRight: "20px" }} >
                <option value="" selected>
                  Buscar por
                </option>
                <option value="Casa">Titulo</option>
                <option value="Universidad">Informacion</option>
              </select>
              <select name="" id="" style={{ marginRight: "20px" }} onChange={searchByCategory}>
                <option value="" selected>
                  Categoria
                </option>
                {categoryQuery.map((category, index) => (
                  <option key={index} value={category.id}>
                    {category.name}
                  </option>
                ))}
              </select>
              <select name="" id="" style={{ marginRight: "20px" }} onChange={searchByStatus}>
                <option value="" selected>
                  Estado
                </option>
                {stateQuery.map((estado, index) => (
                  <option key={index} value={estado.id}>
                    {estado.name}
                  </option>
                ))}
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
