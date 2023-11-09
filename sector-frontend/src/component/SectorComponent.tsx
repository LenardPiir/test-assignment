import {
    Box,
    Button,
    Checkbox,
    Container,
    CssBaseline,
    FormControlLabel,
    Grid,
    TextField,
    Typography
} from "@mui/material";
import {TreeView} from '@mui/x-tree-view/TreeView';
import {TreeItem} from '@mui/x-tree-view/TreeItem';
import {FormEvent, SyntheticEvent, useState} from "react";
import ExpandMoreIcon from '@mui/icons-material/ExpandMore';
import ChevronRightIcon from '@mui/icons-material/ChevronRight';


export default function SectorComponent({sectorService}:any) {
    const [expanded, setExpanded] = useState<string[]>([]);
    const [selected, setSelected] = useState<string[]>([]);

    const handleToggle = (event: SyntheticEvent, nodeIds: string[]) => {
        setExpanded(nodeIds);
    };

    const handleSelect = (event: SyntheticEvent, nodeIds: string[]) => {
        setSelected(nodeIds);
    };

    const handleExpandClick = () => {
        setExpanded((oldExpanded) =>
            oldExpanded.length === 0 ? ['1', '5', '6', '7'] : [],
        );
    };

    const handleSelectClick = () => {
        setSelected((oldSelected) =>
            oldSelected.length === 0 ? ['1', '2', '3', '4', '5', '6', '7', '8', '9'] : [],
        );
    };

    const handleSubmit = (event: FormEvent<HTMLFormElement>) => {
        event.preventDefault();
        const data = new FormData(event.currentTarget);
    };

    return (
        <Container component="main" maxWidth="xs">
            <CssBaseline />
            <Box
                sx={{
                    marginTop: 8,
                    display: 'flex',
                    flexDirection: 'column',
                    alignItems: 'center',
                }}
            >
                <Typography component="h1" variant="h5">
                    Please enter your name and pick the Sectors you are currently involved in.
                </Typography>

                <Box component="form" noValidate onSubmit={handleSubmit} sx={{ mt: 3 }}>
                    <Grid container spacing={2}>
                        <Grid item xs={12}>
                            <TextField
                                required
                                fullWidth
                                name="name"
                                label="Name"
                                type="name"
                                id="name"
                            />
                        </Grid>

                        <Grid item xs={12}>
                            <Typography component="h5" variant="h5">
                                Please select Sector from below *:
                            </Typography>
                        </Grid>

                        <Grid item xs={12}>

                            <TreeView
                                aria-label="controlled"
                                defaultCollapseIcon={<ExpandMoreIcon />}
                                defaultExpandIcon={<ChevronRightIcon />}
                                expanded={expanded}
                                selected={selected}
                                onNodeToggle={handleToggle}
                                onNodeSelect={handleSelect}
                                multiSelect
                            >
                                <TreeItem nodeId="1" label="Manufacturing">
                                    <TreeItem nodeId="2" label="Calendar" />
                                    <TreeItem nodeId="3" label="Chrome" />
                                    <TreeItem nodeId="4" label="Webstorm" />
                                </TreeItem>
                                <TreeItem nodeId="5" label="Other">
                                    <TreeItem nodeId="6" label="MUI">
                                        <TreeItem nodeId="7" label="src">
                                            <TreeItem nodeId="8" label="index.js" />
                                            <TreeItem nodeId="9" label="tree-view.js" />
                                        </TreeItem>
                                    </TreeItem>
                                </TreeItem>
                            </TreeView>
                        </Grid>

                        <Grid item xs={12} mt={1}>
                            <Typography component="h6" variant="h6">
                                Sector selected:
                            </Typography>
                        </Grid>

                        <Grid item xs={12} mt={3}>
                            <FormControlLabel
                                control={<Checkbox value="customerAgreement" color="primary" />}
                                label="Agree to terms *"
                            />
                        </Grid>
                    </Grid>
                    <Button
                        type="submit"
                        fullWidth
                        variant="contained"
                        sx={{ mt: 3, mb: 2 }}
                    >
                        Save
                    </Button>
                </Box>
            </Box>
        </Container>
    );
}