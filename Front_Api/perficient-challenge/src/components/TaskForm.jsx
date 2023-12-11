import React, { useState } from "react";
import "./task-form-style.css";
import TextField from "@mui/material/TextField";
import { AdapterDayjs } from "@mui/x-date-pickers/AdapterDayjs";
import { LocalizationProvider } from "@mui/x-date-pickers/LocalizationProvider";
import { DatePicker } from "@mui/x-date-pickers/DatePicker";
import Button from "@mui/material/Button";
import { sizing } from '@mui/system';



function TaskForm({ createTask }) {
  const [information, setInformation] = useState();
  const [finishDate, setfinishDate] = useState();

  const handleSubmit = (e) => {
    console.log("holiii");
    e.preventDefault();
    const newTask = {
      information,
      finishDate,
    };
    createTask(newTask);
  };

  return (
    <>
    
      <form className="inputs-tasks" onSubmit={handleSubmit}>
      <h3>Crear una tarea</h3>
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

        <LocalizationProvider
          dateAdapter={AdapterDayjs}
        >
          <DatePicker   className="myDatePicker"
          sx={{ marginBottom: "10px"}}
          onChange={() => {
            setfinishDate(e.target.value);
          }} 
          />
        </LocalizationProvider>

        <span>Categoria :</span>
        <TextField
          size="small"
          sx={{ marginBottom: "10px" }}
          id="outlined-basic"
          label="Descripcion de la tarea"
          variant="outlined"
        />

        <span>Estado :</span>
        <TextField
          size="small"
          sx={{ marginBottom: "20px" }}
          id="outlined-basic"
          label="Descripcion de la tarea"
          variant="outlined"
        />

        <Button
          type="submit"
          variant="contained"
          sx={{ backgroundColor: "#1F1F1F", height: "45px", 
          "&:hover": {
            backgroundColor: "#2F2F2F", 
            }}}
        >
          Guardar
        </Button>
      </form>
    </>
  );
}

export default TaskForm;
