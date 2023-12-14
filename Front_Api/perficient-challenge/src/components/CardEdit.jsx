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

function CardEdit({ task }) {

  const [title, setTitle] = useState(task.title);
  const [information, setInformation] = useState("");
  const [finishDate, setfinishDate] = useState(null);
  const [category, setCategory] = useState();
  const [state, setState] = useState("");

  useEffect(() => {
    setCategory(task.category)
  }, [])

  const handleSubmit = (e) => {
    console.log("holiii");
    e.preventDefault();
    const newTask = {
      id: 7,
      title,
      information,
      finishDate: finishDate.toDate(),
      category,
      state,
    };
    console.log(newTask);
    createTask(newTask);
    console.log(closeWindow);
    closeWindow(false)
  };

  const handleChangeCategory = (event) => {
    setCategory(event.target.value);
  };

  const handleChangeState = (event) => {
    setState(event.target.value);
  };



  return (
    <div className="modal-task-edit">
      <div className="modal-task-edit__container">
        {JSON.stringify(task)}
        {console.log(task)}
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
            value={finishDate}
            sx={{ marginBottom: "10px" }}
            onChange={(date) => {
              console.log(Object.prototype.toString.call(date));
              setfinishDate(date);
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
          <MenuItem value={'Universidad'}>Universidad</MenuItem>
          <MenuItem value={'Trabajo'}>Trabajo</MenuItem>
        </Select>

        <span>Estado :</span>
        <Select
          sx={{ marginBottom: "40px" }}
          labelId="demo-simple-select-label-state"
          id="demo-simple-select-state"
          value={state}
          label="Estado"
          onChange={handleChangeState}
        >
          <MenuItem value={'Por hacer'}>Por hacer</MenuItem>
          <MenuItem value={'En proceso'}>En proceso</MenuItem>
          <MenuItem value={'Finalizado'}>Finalizado</MenuItem>
        </Select>

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
      </div>
    </div>
  );
}

export default CardEdit;
