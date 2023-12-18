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
import { MdOutlineCancel } from "react-icons/md";

function CardEdit({ task , closeWindow}) {
  const [title, setTitle] = useState(task.title);
  const [information, setInformation] = useState("");
  const [finishDate, setfinishDate] = useState(null);
  const [category, setCategory] = useState("Universidad");
  const [state, setState] = useState("");

  useEffect(() => {

    setfinishDate(task.date)

    for (const option of document.getElementById("categories").options) {
      if (option.value === task.category) {
        option.selected = true;
        break;
      }
    }

    for (const option of document.getElementById("states").options) {
      if (option.value === task.state) {
        option.selected = true;
        break;
      }
    }

    
  }, []);

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
    
    
    closeWindow(null);
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
            value={task.title}
          />

          <span>Descripcion :</span>

          <TextField
            sx={{ marginBottom: "10px" }}
            size="small"
            id="outlined-basic"
            variant="outlined"
            onChange={(e) => setInformation(e.target.value)}
            value={task.information}
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
          <select name="category" id="categories">
            <option value="Por comprar">Por comprar</option>
            <option value="Universidad">Universidad</option>
            <option value="Trabajo">Trabajo</option>
            <option value="Casa">Casa</option>
          </select>

          <span>Estado :</span>
          <select style={{marginBottom: '20px'}} name="states" id="states">
            <option value="Por hacer">Por hacer</option>
            <option value="En proceso">En proceso</option>
            <option value="Finalizado">Finalizado</option>
          </select>

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
