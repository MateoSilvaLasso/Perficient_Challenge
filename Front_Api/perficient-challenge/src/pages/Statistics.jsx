import React, { useState, useEffect } from "react";
import Chart from "chart.js/auto";
import { CategoryScale } from "chart.js";
import { Pie, Line } from "react-chartjs-2";
import axios from "../config/axios";
import { useGlobalState } from "../index";
import Charts from '../components/Charts';

Chart.register(CategoryScale);

function Statistics() {
  const [data, setData] = useState(null);
  const [helpMe, setHelpMe] = useState();
  const vari = useGlobalState("name")[0];

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await axios.get(`/tasks/${vari}`);
        if (response.status === 200) {
          console.log("Empieza");
          console.log(response.data);
          setData(response.data);
        }
      } catch (error) {
        // Handle errors if necessary
        console.error("Error fetching data:", error);
      }
    };

    fetchData();
  }, [vari]);

  return (
    <div>
      {data ? (
        <Charts Data={data} />
      ) : (
        <p>Loading data...</p>
      )}
    </div>
  );
}

export default Statistics;
