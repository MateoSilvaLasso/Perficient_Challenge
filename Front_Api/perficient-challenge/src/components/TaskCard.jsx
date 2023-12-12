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
    <Card sx={{ maxWidth: 345 }}>
      <CardMedia
        sx={{ height: 140 }}
        image="https://i.pinimg.com/1200x/34/df/ee/34dfeed20d644ba572bd2d8d31bc8d77.jpg"
        title="green iguana"
      />
      <CardContent>
        <Typography gutterBottom variant="h5" component="div">
          {task.id}
        </Typography>
        <Typography variant="body2" color="text.secondary">
          {task.information}
        </Typography>
        <Typography variant="body2" color="text.secondary">
          {task.finishDate.toLocaleDateString()}
        </Typography>
      </CardContent>
      <CardActions>
        <Button size="small">Share</Button>
        <Button size="small">Learn More</Button>
      </CardActions>
    </Card>
  );
}
