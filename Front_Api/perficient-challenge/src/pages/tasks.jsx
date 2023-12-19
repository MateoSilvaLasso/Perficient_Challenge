import React from "react";
import { useState, useEffect } from "react";
import TasksList from "../components/tasksList";
import TaskForm from "../components/TaskForm";
import { connect } from 'react-redux';
import axios from  '../config/axios'
import { data } from "../components/tasks.js";
import ButtonAppBar from "../components/Header.jsx";
import { IoAddCircleOutline } from "react-icons/io5";
import { MdOutlineCancel } from "react-icons/md";
import Notifications from "../components/Notifications.jsx";
import { IoIosSearch } from "react-icons/io";
import CardEdit from "../components/CardEdit.jsx";
import { useAuth } from '../pages/authContext';
import "./task-styles.css";
import store from '../store';
import {useGlobalState, setGlobalState} from '../index'
import { useNavigate } from 'react-router-dom';

function Tasks() {
  const [tasks, setTasks] = useState([]);
  const [addTaskVisibility, setAddTaskVisibility] = useState(false);
  const [categoryQuery, setCategoryQuery] = useState([]);
  const [stateQuery, setStateQuery] = useState([]);
  const [cardDescription, setCardDescription] = useState(null);

  
  const vari = useGlobalState("name")[0]
  let navigate = useNavigate();
 
  useEffect(() => {
   
    
    //console.log(vari)
    
    axios.get(`/tasks/${vari}`).then(res =>{
        if(res.status === 200){
          console.log(res.data)
          setTasks(res.data)
        }
    })

    axios.get("/status").then(res =>{
      if(res.status === 200){
        console.log(res.data)
        setStateQuery(res.data)
      }
    })

    axios.get("/category").then(res =>{
      if(res.status === 200){
        console.log(res.data)
        setCategoryQuery(res.data)
      }
    })

    //setTasks(data);
  }, []);

  function createTask(task,category,status) {
    axios.post(`/tasks/${category}/${status}/${vari}`, task).then(res =>{
      if(res.status === 200){
          console.log(res.data)
          navigate("/App")
      }
    })
  }

  useEffect(() => {
    console.log('Ayudame dios')
    console.log(cardDescription)
  }, [cardDescription]);

  return (
    <div>
      {cardDescription !== null? (
        <CardEdit task = {cardDescription} closeWindow = {setCardDescription}/> 
      ): null}
      
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
          <Notifications />
        </div>

        <div className="task-list">
          <div className="nav-bar-section">
            <div className="seach-bar-container">
              <input style={{marginRight: '40px', width: '40rem'}}
                placeholder="Escribe tu busqueda"
                className="search-bar"
                type="text"
              />
              <select name="" id=""   style={{marginRight: '20px'}}>
                <option value="" selected>Buscar por</option>
                <option value="Casa">Titulo</option>
                <option value="Universidad">Informacion</option>
              </select>
              <select name="" id="" style={{marginRight: '20px'}}>
                <option value="" selected>Categoria</option>
                {categoryQuery.map((category, index) => (
                  <option key={index} value={category.id}>
                    {category.name}
                  </option>
                ))}
                
              </select>
              <select name="" id="" style={{ marginRight: '20px' }}>
                <option value="" selected>Estado</option>
                {stateQuery.map((estado, index) => (
                  <option key={index} value={estado.id}>
                    {estado.name}
                  </option>
                ))}
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
          <TasksList tasks={tasks} openCardEdit = {setCardDescription} />
        </div>
      </section>
    </div>
  );
}

export default Tasks;
