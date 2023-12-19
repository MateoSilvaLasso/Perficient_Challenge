import React, { useEffect, useState } from "react";
import "./task-form-style.css";
import TextField from "@mui/material/TextField";
import { AdapterDayjs } from "@mui/x-date-pickers/AdapterDayjs";
import { LocalizationProvider } from "@mui/x-date-pickers/LocalizationProvider";
import { DatePicker } from "@mui/x-date-pickers/DatePicker";
import Button from "@mui/material/Button";
import MenuItem from "@mui/material/MenuItem";
import Select from "@mui/material/Select";
import { MdOutlineCancel } from "react-icons/md";
import { categories } from "./Categories";
import axios from '../config/axios';

function TaskForm({ createTask, closeWindow }) {
  const [title, setTitle] = useState("");
  const [information, setInformation] = useState("");
  const [endtask, setEndTask] = useState(null);
  const [category, setCategory] = useState([]);
  const [state, setState] = useState([]);
  const [stateQuery, setStateQuery] = useState("")
  const [categoryQuery, setCategoryQuery] = useState("")

  useEffect (()=>{

    axios.get("/status").then(res =>{
      if(res.status === 200){
        //console.log(res.data)
        setState(res.data)
      }
    })

    axios.get("/category").then(res =>{
      if(res.status === 200){
        console.log(res.data)
        setCategory(res.data)
      }
    })

  },[])

  const handleSubmit = (e) => {
    console.log("holiii");
    e.preventDefault();
    const newTask = {
      title: title,
      information: information,
      endtask: endtask.toDate(),
    };
    console.log(newTask);
    createTask(newTask,categoryQuery,stateQuery);
    console.log(closeWindow);
    closeWindow(false);
  };

  const handleChangeCategory = (event) => {
    setCategoryQuery(event.target.value);
  };

  const handleChangeState = (event) => {
    setStateQuery(event.target.value);
  };

  return (
    <>
      <form className="inputs-tasks" onSubmit={handleSubmit}>
        <h3>Crear una tarea</h3>

        <span>Titulo :</span>

        <TextField
          sx={{ marginBottom: "10px" }}
          size="small"
          id="outlined-basic"
          label="Titulo de la tarea"
          variant="outlined"
          onChange={(e) => setTitle(e.target.value)}
        />

        <span>Descripcion :</span>

        <TextField
          sx={{ marginBottom: "10px" }}
          size="small"
          id="outlined-basic"
          label="Descripcion de la tarea"
          variant="outlined"
          onChange={(e) => setInformation(e.target.value)}
        />

        <span>Fecha de finalizacion :</span>

        <LocalizationProvider dateAdapter={AdapterDayjs}>
          <DatePicker
            className="myDatePicker"

            value={endtask}
            sx={{ marginBottom: "10px" }}
            onChange={(date) => {
              console.log(Object.prototype.toString.call(date));
              setEndTask(date);
            }}
          />
        </LocalizationProvider>

        <div style={{ marginBottom: "10px", display: "flex",  }}>
          <p style={{marginRight: '1rem'}}>Repetir semanalmente</p>
          <input  type="radio" name="" id="" />
        </div>

        <span>Categoria :</span>
        <Select
          sx={{ marginBottom: "10px" }}
          labelId="demo-simple-select-label-category"
          id="demo-simple-select-category"
          value={category}
          label="Categoria"
          onChange={handleChangeCategory}
        >

          <MenuItem value={'Por comprar'}>Por comprar</MenuItem>
          {category.map((category, index) => (
            <MenuItem key={index} value={category.id}>
              {category.name}
            </MenuItem>
          ))}
        </Select>

        <span>Estado :</span>
        <Select
          sx={{ marginBottom: "10px" }}
          labelId="demo-simple-select-label-state"
          id="demo-simple-select-state"
          value={state}
          label="Estado"
          onChange={handleChangeState}
        >
          <MenuItem value="" disabled>
            Estado
          </MenuItem>
          {state.map((estado, index) => (
            <MenuItem key={index} value={estado.id}>
              {estado.name}
            </MenuItem>
          ))}
        </Select>

        <span>Archivo: </span>
        <input style={{ marginBottom: "20px" }} type="file" name="" id="" />

        <Button
          type="submit"
          variant="contained"
          sx={{
            backgroundColor: "#1F1F1F",
            height: "45px",
            "&:hover": {
              backgroundColor: "#2F2F2F",
            },
          }}
        >
          Guardar
        </Button>
      </form>
    </>
  );
}

export default TaskForm;
