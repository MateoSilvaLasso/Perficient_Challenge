import "./card-edit-styles.css";
import React, { useEffect, useState } from "react";
import "./task-form-style.css";
import TextField from "@mui/material/TextField";
import { AdapterDayjs } from "@mui/x-date-pickers/AdapterDayjs";
import { LocalizationProvider } from "@mui/x-date-pickers/LocalizationProvider";
import { DatePicker } from "@mui/x-date-pickers/DatePicker";
import Button from "@mui/material/Button";
import MenuItem from "@mui/material/MenuItem";
import Select from "@mui/material/Select";
import axios from  '../config/axios'
import { MdOutlineCancel } from "react-icons/md";

function CardEdit({ task , closeWindow, editTask}) {
  const [title, setTitle] = useState(task.title);
  const [information, setInformation] = useState(task.information);
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
        //console.log(res.data)
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
    console.log(stateQuery)
    editTask(newTask,task.id,categoryQuery,stateQuery)
    closeWindow(null);
  };

  const handleChangeCategory = (event) => {
    setCategoryQuery(event.target.value);
  };

  const handleChangeState = (event) => {
    setStateQuery(event.target.value);
  };

  

  return (
    <div className="modal-task-edit">
      <div className="modal-task-edit__container">
        
        <form className="inputs-tasks" onSubmit={handleSubmit}>

        <div style={{ display: "flex", justifyContent: "flex-end" }}>
              <button
                id="close"
                onClick={() => {
                  closeWindow(null);
                }}
              >
                <MdOutlineCancel size="30px" />
              </button>
            </div>
          <h3>Modificar tarea</h3>

          <span>Titulo :</span>

          <TextField
            sx={{ marginBottom: "10px" }}
            size="small"
            id="outlined-basic"
            variant="outlined"
            onChange={(e) => setTitle(e.target.value)}
            label={task.title}
          />

          <span>Descripcion :</span>

          <TextField
            sx={{ marginBottom: "10px" }}
            size="small"
            id="outlined-basic"
            variant="outlined"
            onChange={(e) => setInformation(e.target.value)}
            label={task.information}
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

          <div style={{display: 'flex', justifyContent: 'space-between'}}>
          <Button style={{width: '45%'}}
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
            Borrar
          </Button>
          <Button 
          style={{width: '45%'}}
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
            Editar
          </Button>
          </div>
        </form>
      </div>
    </div>
  );
}

export default CardEdit;
