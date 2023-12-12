import * as React from 'react';
import Card from '@mui/material/Card';
import CardActions from '@mui/material/CardActions';
import CardContent from '@mui/material/CardContent';
import CardMedia from '@mui/material/CardMedia';
import Button from '@mui/material/Button';
import Typography from '@mui/material/Typography';
import { format } from 'date-fns';

export default function MediaCard({task}) {

    console.log(task)

  return (
    <Card sx={{ maxWidth: 300,  minWidth: 250 }}>
      <CardMedia
        sx={{ height: 25 }}
        image="https://bangbranding.com/blog/wp-content/uploads/2016/09/350x500_destacada-1.jpg"
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
          {task.finishDate.toLocaleDateString()}
        </Typography>
      </CardContent>
      <CardActions>
      <div style={{display: 'flex', justifyContent: 'space-between', boxSizing: 'border-box' , width: '100%'}}>
        <p>{task.category}</p>
        <p>{task.state}</p>
      </div>
      </CardActions>
    </Card>
  );
}
