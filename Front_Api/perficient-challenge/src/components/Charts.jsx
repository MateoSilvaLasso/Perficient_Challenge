import React from "react";
import ButtonAppBar from "../components/Header";
import { useState, useEffect } from "react";
import Chart from "chart.js/auto";
import { CategoryScale } from "chart.js";
import { Pie, Line } from "react-chartjs-2";
import axios from "../config/axios";
import { useGlobalState, setGlobalState } from "../index";
import { useNavigate } from "react-router-dom";

Chart.register(CategoryScale);

function Charts({Data}) {

  const [helpMe, setHelpMe] = useState([1,1,1,1])
  let vari = useGlobalState("name")[0];
  let navigate = useNavigate();
  const uniqueStates = Array.from(new Set(Data.map((task) => task.status.name)));
  

    console.log('uniqueStates', uniqueStates)
  
    // Count tasks for each state
  const stateCounts = uniqueStates.map((state) => {
      return Data.reduce(
        (count, task) => count + (task.status.name === state ? 1 : 0),
        0
      );
    });

  console.log('stateCounts', stateCounts)

  const uniqueCategories = Array.from(
    new Set(Data.map((task) => task.category.name))
  );
  console.log( 'uniqaaaaa', uniqueCategories)

  // Count tasks for each state
  const categoryCounts = uniqueCategories.map((state) => {
    return Data.reduce(
      (count, task) => count + (task.category.name === state ? 1 : 0),
      0
    );
  });

  console.log('category countsss', categoryCounts)

  const [chartDataTaskFinished, setChartDataTaskFinished] = useState({
    labels: ["Por hacer", "En proceso", "Cancelado", "Finalizado"],
    datasets: [
      {
        label: "Tareas ",
        data: stateCounts,
        backgroundColor: [
          "rgba(0,0,0,0.5)",
          "rgba(0,0,0,0.3)",
          "rgba(0,0,0,0.1)",
        ],
        borderWidth: 2,
      },
    ],
  });

  const [chartDataCategory, setchartDataCategory] = useState({
    labels: uniqueCategories,
    datasets: [
      {
        label: "Tareas ",
        data: categoryCounts,
        backgroundColor: [
          "rgba(0,0,0,0.5)",
          "rgba(0,0,0,0.3)",
          "rgba(0,0,0,0.1)",
        ],
        borderWidth: 2,
      },
    ],
  });

  const monthCounts = [0,0,0,0,0,0,0,0,0,0,0,5];
  Data.forEach((task) => {
    const month = new Date(task.endTask).getMonth(); // Get the month as a number (0-11)
    if (!monthCounts[month]) monthCounts[month] = 0;
    monthCounts[month]++;
  });
  
  const [chartDataTaskByDate, setchartDataTaskByDate] = useState({
    labels: [
        'Enero',
        'Febrero',
        'Marzo',
        'Abril',
        'Mayo',
        'Junio',
        'Julio',
        'Agosto',
        'Septiembre',
        'Octubre',
        'Noviembre',
        'Diciembre'
      ],
    datasets: [
      {
        label: "Tareas ",
        data: monthCounts,
        backgroundColor: [
          "rgba(0,0,0,0.5)",
          "rgba(0,0,0,0.3)",
          "rgba(0,0,0,0.1)",
        ],
        tension: 0.1,
        borderWidth: 2,
      },
    ],
  });

  

  console.log(monthCounts); // This will print an object with month numbers as keys and task counts as values

  return (
    <div>
      <ButtonAppBar />
      <div style={{ paddingTop: "2rem", width: "50%", margin: "auto" }}>
        <h1 style={{ marginBottom: "2rem" }}>
          {" "}
          <b>Estadisticas</b>{" "}
        </h1>

        <div
          style={{
            display: "flex",
            justifyContent: "space-around",
            flexWrap: "wrap",
          }}
        >
          <div
            style={{
              width: "48%",
              borderRadius: "10px",
              padding: "1rem",
              border: "1px solid rgba(0,0,0,0.5)",
              display: "flex",
            }}
          >
            <Pie
              style={{ width: "700px" }}
              data={chartDataTaskFinished}
              options={{
                plugins: {
                  title: {
                    display: true,
                    text: "Tareas terminadas y no terminadas",
                  },
                },
              }}
            />
            {console.log('AYUDAAAAA')}
            
          </div>

          <div
            style={{
              width: "48%",
              borderRadius: "10px",
              padding: "1rem",
              border: "1px solid rgba(0,0,0,0.5)",
              display: "flex",
            }}
          >
            <Pie
              style={{ width: "700px" }}
              data={chartDataCategory}
              options={{
                plugins: {
                  title: {
                    display: true,
                    text: "Tareas por categorias",
                  },
                },
              }}
            />
          </div>
          <div
            style={{
              marginTop: "20px",
              width: "98%",
              borderRadius: "10px",
              padding: "1rem",
              border: "1px solid rgba(0,0,0,0.5)",
              display: "flex",
            }}
          >
            <Line
              data={chartDataTaskByDate}
              options={{
                plugins: {
                  title: {
                    display: true,
                    text: "Tareas con fecha de finalizacion por mes",
                  },
                  legend: {
                    display: false,
                  },
                },
              }}
            />
            
          </div>
        </div>
      </div>
    </div>
  );
}

export default Charts;
