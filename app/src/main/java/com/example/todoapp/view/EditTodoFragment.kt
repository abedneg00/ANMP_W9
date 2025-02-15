package com.example.todoapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.todoapp.R
import com.example.todoapp.databinding.FragmentCreateTodoBinding
import com.example.todoapp.databinding.FragmentEditTodoBinding
import com.example.todoapp.model.Todo
import com.example.todoapp.viewmodel.DetailTodoViewModel


class EditTodoFragment : Fragment(), RadioClickListener, TodoEditClickListener {
    private lateinit var binding: FragmentEditTodoBinding
    private lateinit var viewModel: DetailTodoViewModel
//    private lateinit var todo:Todo

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEditTodoBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(DetailTodoViewModel::class.java)
        binding.txtJudulTodo.text = "Edit ToDo"
        val uuid = EditTodoFragmentArgs.fromBundle(requireArguments()).uuid
        viewModel.fetch(uuid)
        observeViewModel()

//        binding.btnCreate.setOnClickListener {
//            val radio =
//                view.findViewById<RadioButton>(binding.radioGroupPriority.checkedRadioButtonId)
//            viewModel.update(binding.txtTitle.text.toString(), binding.txtNotes.text.toString(),
//                radio.tag.toString().toInt(), uuid)
//            Toast.makeText(view.context, "Todo updated", Toast.LENGTH_SHORT).show()
//            Navigation.findNavController(it).popBackStack()
//        }

        // WEEK 10
//        binding.btnCreate.setOnClickListener {
//            todo.title = binding.txtTitle.text.toString()
//            todo.notes = binding.txtNotes.text.toString()
//            val radio =
//                view.findViewById<RadioButton>(binding.radioGroupPriority.checkedRadioButtonId)
//            todo.priority = radio.tag.toString().toInt()
//            viewModel.update(todo)
//            Toast.makeText(view.context, "Todo updated", Toast.LENGTH_SHORT).show()
//            Navigation.findNavController(it).popBackStack()
//        }

        binding.radioListener = this
        binding.saveListener = this

    }

    fun observeViewModel() {
        viewModel.todoLD.observe(viewLifecycleOwner, Observer {
//            todo = it
//
//            binding.txtTitle.setText(it.title)
//            binding.txtNotes.setText(it.notes)
//
//            when (it.priority) {
//                1 -> binding.radioLow.isChecked = true
//                2 -> binding.radioMedium.isChecked = true
//                else -> binding.radioHigh.isChecked = true
//            }
            binding.todo = it
        })
    }

    override fun onRadioClick(v: View) {

        binding.todo!!.priority = v.tag.toString().toInt()

        //cara di bawah ini tidak efektif karena kita harus mengecek radio button ke berapa
//        var radio = v as RadioButton
//        if(v.id == R.id.radioHigh && radio.isChecked){
//            binding.todo.priority = 3
//        }
    }
    override fun onTodoEditClick(v: View) {
        viewModel.update(binding.todo!!)
    }
}