import * as React from 'react';
import { DataGrid } from '@mui/x-data-grid';
import { Container, Paper, Box } from '@mui/material';
import { getListOfNamesAndTasks } from './getListOfNamesAndTasks';
import Button from '@mui/material/Button';

const columns = [
  { field: 'names', headerName: 'Name', width: 150 },
  { field: 'tasks', headerName: 'Task', width: 150 },
];


// const rows = [
//   { id: getListOfNamesAndTasks.id, names: getListOfNamesAndTasks.name, tasks: getListOfNamesAndTasks.tasks},
// ];

export default function DataTable() {
  const [rows, setRows] = React.useState([]);

  React.useEffect(() => {
    const fetchData = async () => {
      try {
        const dataTasks = await getListOfNamesAndTasks();

        if (dataTasks) {
          setRows(dataTasks);
        }
      } catch (e) {
        console.error("Error fetching tasks: ", e);
      }
    };

    fetchData();
  }, []);


  return (
    <Container
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
      <h2 style={{color: "#707070"}}>List of tasks: </h2>
        <Box style={{ height: 400, width: '100%'}}>
          <DataGrid
            rows={rows}
            columns={columns}
            initialState={{
              pagination: {
                paginationModel: { page: 0, pageSize: 5 },
              },
            }}
            pageSizeOptions={[5, 10]}
            checkboxSelection
          />
        </Box>
        <Box sx={{ display: 'flex', justifyContent: 'center', alignItems: 'center', height: '50px' }}>
          <Button 
            variant="contained" 
            type="submit" 
            sx={{
              backgroundColor: '#338BA8',
              '&:hover': {
                backgroundColor: '#296E85',
              },
            }}
          >Delete tasks</Button>
        </Box>
      </Paper>
    </Container>
  );
}
