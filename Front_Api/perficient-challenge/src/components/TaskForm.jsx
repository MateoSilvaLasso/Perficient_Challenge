import React, { useState } from "react";
import "./task-form-style.css";
import TextField from '@mui/material/TextField';
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs';
import { LocalizationProvider } from '@mui/x-date-pickers/LocalizationProvider';
import { DatePicker } from '@mui/x-date-pickers/DatePicker';
import Button from '@mui/material/Button';



function TaskForm({ createTask }) {
  const [information, setInformation] = useState();
  const [finishDate, setfinishDate] = useState();

  const handleSubmit = (e) => {
    console.log("holiii")
    e.preventDefault();
    const newTask = {
      information, 
      finishDate
    };
    createTask(newTask);
  };

  return (
    <>
      <form className="inputs-tasks" onSubmit={handleSubmit}>
        <TextField
          id="outlined-basic"
          label="Descripcion de la tarea"
          variant="outlined"
          onChange={(e) => setInformation(e.target.value)}
        />

        <span>Fecha de finalizacion:</span>
        
        <LocalizationProvider dateAdapter={AdapterDayjs} onChange={() => {
            setfinishDate(e.target.value);
          }}>
            <DatePicker />
        </LocalizationProvider>
        <Button type="submit" variant="contained">Guardar</Button>
      </form>
    </>
  );
}

export default TaskForm;
