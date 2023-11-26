<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Página Web Modificable</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      margin: 0;
      padding: 0;
      display: flex;
      height: 100vh;
    }

    #sidebar {
      width: 150px;
      background-color: #333;
      color: white;
      padding: 20px;
      display: flex;
      flex-direction: column;
      align-items: center;
    }

    #content {
      flex: 1;
      padding: 20px;
    }

    button {
      margin-bottom: 10px;
      padding: 10px;
      width: 100%;
      background-color: #4CAF50;
      color: white;
      border: none;
      border-radius: 5px;
      cursor: pointer;
    }

    #panel {
      margin-top: 20px;
    }

    table {
      width: 100%;
      border-collapse: collapse;
      margin-top: 20px;
    }

    th, td {
      border: 1px solid #ddd;
      padding: 8px;
      text-align: left;
    }

    th {
      background-color: #333;
      color: white;
    }
  </style>
</head>
<body>

  <div id="sidebar">
    <h2>Acciones</h2>
    <button onclick="loadData('button1')">Botón 1</button>
    <button onclick="loadData('button2')">Botón 2</button>
    <button onclick="loadData('button3')">Botón 3</button>
    <button onclick="loadData('button4')">Botón 4</button>
    <button onclick="loadData('button5')">Botón 5</button>
    <button onclick="loadData('button6')">Botón 6</button>
    <button onclick="loadData('button7')">Botón 7</button>
    <button onclick="loadData('button8')">Botón 8</button>
    <button onclick="loadData('button9')">Botón 9</button>
    <button onclick="loadData('button10')">Botón 10</button>
  </div>

  <div id="content">
    <h1>Página Web Modificable</h1>
    <div id="panel">
      <h2>Panel de Datos</h2>
      <table id="data-table">
        <thead>
          <tr>
            <th>Columna 1</th>
            <th>Columna 2</th>
            <!-- Añadir más columnas según sea necesario -->
          </tr>
        </thead>
        <tbody id="table-body">
          <!-- Aquí se cargarán los datos -->
        </tbody>
      </table>
    </div>
  </div>

  <script>
    function loadData(buttonId) {
      // Lógica para cargar datos según el botón presionado
      // Puedes hacer una solicitud a una API aquí y actualizar la tabla con los resultados
      // Por ejemplo, usando fetch() para obtener datos JSON

      // Ejemplo de cómo actualizar la tabla
      var data = [
        { columna1: 'Dato1', columna2: 'Dato2' },
        { columna1: 'Dato3', columna2: 'Dato4' },
        // Añadir más filas según sea necesario
      ];

      var tableBody = document.getElementById('table-body');
      tableBody.innerHTML = '';

      data.forEach(function (row) {
        var newRow = document.createElement('tr');
        newRow.innerHTML = '<td>' + row.columna1 + '</td><td>' + row.columna2 + '</td>';
        tableBody.appendChild(newRow);
      });
    }
  </script>

</body>
</html>
