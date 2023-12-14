import * as React from "react";
import Card from "@mui/material/Card";
import CardActions from "@mui/material/CardActions";
import CardContent from "@mui/material/CardContent";
import CardMedia from "@mui/material/CardMedia";
import Button from "@mui/material/Button";
import Typography from "@mui/material/Typography";
import { FaRegCircleCheck } from "react-icons/fa6";
import { TbProgress } from "react-icons/tb";
import { GoTasklist } from "react-icons/go";
import { format } from "date-fns";

export default function MediaCard({ task }) {
  console.log(task);

  return (
    <Card sx={{ maxWidth: 300, minWidth: 250, marginBottom: "2rem" }} style={{cursor: 'pointer'}} onClick = {() => {
      alert()
    }} >
      <CardMedia
        sx={{ height: 25 }}
        image="https://cartaral.com/wp-content/uploads/2021/05/RAL-9023-Gris-oscuro-perlado.jpg"
        title="green iguana"
      />
      <CardContent>
        <Typography gutterBottom variant="h6" component="div">
          <b>{task.title}</b>
        </Typography>
        <Typography variant="body2" color="text.secondary">
          {task.information}
        </Typography>
        <Typography variant="body2" color="text.secondary">
          {task.finishDate.toLocaleDateString("en-US", {
            year: "numeric",
            month: "numeric",
            day: "numeric",
            hour: "numeric",
            minute: "numeric",
            hour12: true, // Use 24-hour time format
          })}
        </Typography>
      </CardContent>
      <CardActions>
        <div
          style={{
            display: "flex",
            justifyContent: "space-between",
            boxSizing: "border-box",
            width: "100%",
          }}
        >
          <p>{task.category}</p>

          {task.state === "Finalizado" ? (
            <FaRegCircleCheck size="25px" />
          ) : task.state === "En proceso" ? (
            <TbProgress size="25px" />
          ) : task.state === "Por hacer" ? (
            <GoTasklist size="25px" />
          ) : (
            task.state
          )}
        </div>
      </CardActions>
    </Card>
  );
}
