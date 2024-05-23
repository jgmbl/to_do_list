import * as React from 'react';
import Stack from '@mui/material/Stack';
import Button from '@mui/material/Button';

export default function ButtonMainMenu() {
  return (
    <Stack spacing={2} direction="column">
      <Button variant="contained" sx={{
        backgroundColor: '#338BA8',
        '&:hover': {
            backgroundColor: '#296E85',
          },
        }}>Add task</Button>
    </Stack>
  );
}