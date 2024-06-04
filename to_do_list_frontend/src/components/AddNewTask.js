import * as React from 'react';
import TextField from '@mui/material/TextField';
import NamesDropDownMenu from './NamesDropDownMenu';
import ButtonMainMenu from './ButtonMainMenu';
import { Container, Paper, Stack } from '@mui/material';

export default function AddNewTask() {
    const [task, setTask] = React.useState('');

    function handleTaskChange(event) {
        setTask(event.target.value);
    }

    function handleSubmit(event) {
        event.preventDefault();

        console.log("Task: ", task);
    }
    
  return (
    <Container
      component="form"
      onSubmit={handleSubmit}
      sx={{
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'center',
        '& > :not(style)': { m: 1, width: '75ch'},
      }}
      noValidate
      autoComplete="off"
    >
        <Paper elevation={3} sx={{ p: 3 }}>
            <h1 style={{color: "#338BA8"}}>Welcome to to-do list app!</h1>
            <p style={{color: "#707070"}}>Create to-do lists and assign tasks to users.</p>
            <p style={{color: "#707070"}}>Check the tasks in the menu in the top left corner.</p>
            <Stack spacing={2}>
            <b style={{color: "#707070"}}>Select name from the list...</b>
              <NamesDropDownMenu sx={{justifyContent: 'center'}}/>
              <b style={{color: "#707070"}}>...or type your name:</b>
            <TextField id="name" label="Name" variant="outlined" />
            <b style={{color: "#707070"}}>Enter the task:</b>
            <TextField 
            error={task.length === 0}
            helperText={!task.length ? 'Task is required' : ''}
            id="task"
            label="Task"
            variant="outlined"
            value={task}
            onChange={handleTaskChange}/>
            <ButtonMainMenu/>
            </Stack>
      </Paper>
    </Container>
  );
}