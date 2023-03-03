function changeActive(clickedButton){
    // Add 'active' to the clicked button
    clickedButton.classList.add('active');
    // remove class 'active' for each siblings
    let sibs = getAllSiblings(clickedButton);
    removeClass(sibs, "active");
}

function getAllSiblings(e){
    // for collecting siblings
    let siblings = []; 
    // if no parent, return no sibling
    if(!e.parentNode) {
        return siblings;
    }
    // first child of the parent node
    let sibling  = e.parentNode.firstChild;
    
    // collecting siblings
    while (sibling) {
        if (sibling.nodeType === 1 && sibling !== e) {
            siblings.push(sibling);
        }
        sibling = sibling.nextSibling;
    }
    return siblings;
};

function removeClass(elements, className){
    elements.forEach(element => {
        element.classList.remove(className);
    });
}